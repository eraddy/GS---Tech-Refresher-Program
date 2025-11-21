# 📘 Mongo DB Notes
*A complete beginner-to-advanced reference for MongoDB CRUD, Operators, Indexing, Aggregation, Array Queries, and Spring Boot Integration.*

---

## 📑 Table of Contents
1. [Introduction](#introduction)
2. [MongoDB Basics](#mongodb-basics)
3. [CRUD Operations](#crud-operations)
4. [Array Querying](#array-querying)
5. [Query Operators](#query-operators)
6. [Sorting & Pagination](#sorting--pagination)
7. [Aggregation Pipeline](#aggregation-pipeline)
8. [Indexing](#indexing)
9. [Full Text Search](#full-text-search)
10. [Spring Boot + MongoDB](#spring-boot--mongodb)
11. [Best Practices](#best-practices)
12. [License](#license)

---

## 🧭 Introduction
**Mongo DB Notes** is a concise and structured reference designed to help developers master MongoDB from the ground up.  
It covers key concepts, CRUD operations, aggregation, indexing, array queries, operators, and integration with Spring Boot.

---

## 📚 MongoDB Basics

### ✔ What is MongoDB?
- NoSQL document-oriented database
- Stores data as **BSON**
- Schema-less and highly flexible
- Horizontally scalable
- Supports **indexing**, **aggregation**, **transactions (limited)**, **replication**, **sharding**, and **full-text search**

### ✔ Example Document
```json
{
  "_id": "122",
  "name": "Aditya",
  "bio": "Working hard to get success"
}
```

---

## 📚 CRUD Operations

### ➤ INSERT
```js
db.students.insertOne({
  name: "Arjun",
  age: 15,
  class: "10th",
  subjects: ["Science", "Maths", "Computers"]
});
```

### ➤ FIND
```js
db.students.find({ age: 15 });
db.students.findOne({ name: "Arjun" });

// Projection
db.students.find({}, { name: 1, age: 1, _id: 0 });
```

### ➤ UPDATE

#### ✔ Set a field
```js
db.students.updateOne(
  { name: "Vikram" },
  { $set: { isEligible: true } }
);
```

#### ✔ Unset a field
```js
db.students.updateOne(
  { name: "Vikram" },
  { $unset: { idcards: "" } }
);
```

#### ✔ Increment
```js
db.students.updateOne(
  { name: "Arjun" },
  { $inc: { age: 1 } }
);
```

#### ✔ Push into array
```js
db.students.updateOne(
  { name: "Arjun" },
  { $push: { subjects: "Biology" } }
);
```

#### ✔ Pull from array
```js
db.students.updateOne(
  { name: "Arjun" },
  { $pull: { subjects: "Maths" } }
);
```

### ➤ DELETE
```js
db.students.deleteOne({ name: "Arjun" });
```

---

## 📚 Array Querying

### ✔ Check if array contains a value
```js
db.students.find({ subjects: "Maths" });
```

### ✔ Query nested array using `$elemMatch`
Use only when **multiple conditions must match in the same array element**.

```js
db.orders.find({
  items: {
    $elemMatch: { price: { $gt: 100 }, qty: { $gte: 2 } }
  }
});
```

---

## 📚 Query Operators

### Comparison Operators
```js
{ age: { $gt: 18 } }
{ age: { $lt: 30 } }
{ age: { $gte: 20 } }
{ age: { $ne: 15 } }
```

### Logical Operators
```js
db.students.find({
  $and: [{ age: { $gt: 18 } }, { class: "12th" }]
});

db.students.find({
  $or: [{ age: 15 }, { age: 16 }]
});
```

### Exists Operator
```js
db.students.find({ bio: { $exists: true } });
```

---

## 📚 Sorting & Pagination

### ✔ Sorting
```js
db.students.find().sort({ age: 1 }); // 1 = ascending, -1 = descending
```

### ✔ Pagination
```js
db.students.find().skip(10).limit(5);
```

---

## 📚 Aggregation Pipeline

### Basic Example
```js
db.students.aggregate([
  { $match: { class: "12th" } },
  { $group: { _id: "$age" } }
]);
```

### Add `$push`
```js
db.students.aggregate([
  { $match: { class: "12th" } },
  {
    $group: {
      _id: "$age",
      students: { $push: "$name" }
    }
  }
]);
```

### Useful Pipeline Stages

| Stage     | Purpose                  |
|-----------|---------------------------|
| `$match`  | Filter documents          |
| `$group`  | Group documents           |
| `$project`| Select/transform fields   |
| `$sort`   | Sort results              |
| `$limit` / `$skip` | Pagination       |
| `$unwind` | Flatten arrays            |

---

## 📚 Indexing

### ✔ Create Index
```js
db.students.createIndex({ name: 1 });
```

### ✔ Compound Index
```js
db.students.createIndex({ class: 1, age: -1 });
```

### ✔ Check Indexes
```js
db.students.getIndexes();
```

---

## 📚 Full Text Search

### Create Text Index
```js
db.users.createIndex({ name: "text", bio: "text" });
```

### Perform Text Search
```js
db.users.find({
  $text: { $search: "hardworking success" }
});
```

### Sort by Text Score
```js
db.users.find(
  { $text: { $search: "success" } },
  { score: { $meta: "textScore" } }
).sort({ score: { $meta: "textScore" } });
```

---

## 📚 Spring Boot + MongoDB

### ✔ Entity
```java
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String bio;
}
```

### ✔ Repository
```java
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByName(String name);
}
```

### ✔ Service
```java
@Autowired
UserRepository repo;

public User saveUser(User user){
    return repo.save(user);
}

public User findById(String id){
    return repo.findById(id).orElse(null);
}
```

### ✔ Controller
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return service.findById(id);
    }

    @PostMapping
    public User save(@RequestBody User user){
        return service.saveUser(user);
    }
}
```

---

## 🏆 MongoDB Best Practices

- Use **indexes** on frequently queried fields
- Avoid **large documents (>16MB)**
- Use `$elemMatch` only when necessary
- Prefer **aggregation pipeline** for analytical queries
- Use **ObjectId** for `_id` unless required otherwise
- Avoid `$where` (slow and unsafe)
- Use **projection** to reduce network load

---
