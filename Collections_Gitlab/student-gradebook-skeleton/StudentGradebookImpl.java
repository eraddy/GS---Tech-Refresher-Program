package com.epam.rd.autocode.collections;

import java.math.BigDecimal;
import java.util.*;


public class StudentGradebookImpl implements StudentGradebook {

	private Map<Student, Map<String, BigDecimal>> map;

    Comparator<Student> studentComparator;

	public StudentGradebookImpl() {
        this.studentComparator = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int cmp = s1.getLastName().compareTo(s2.getLastName());
                if (cmp == 0) {
                    cmp = s1.getFirstName().compareTo(s2.getFirstName());
                }
                if (cmp == 0) {
                    cmp = s1.getGroup().compareTo(s2.getGroup());
                }
                return cmp;
            }
        };

        this.map = new TreeMap<>(studentComparator);
    }

	@Override
	public boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade) {
        if (!map.containsKey(student)) {
            map.put(student, new HashMap<String, BigDecimal>());
        }
        Map<String, BigDecimal> grades = map.get(student);
        if(grades.containsKey(discipline) && Objects.equals(grades.get(discipline), grade))
            return false;
        grades.put(discipline, grade);
        return true;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Comparator<Student> getComparator() {
		return studentComparator;
	}

    @Override
    public List<String> getStudentsByDiscipline(String discipline) {
        List<String> result = new ArrayList<>();

        for (Map.Entry<Student, Map<String, BigDecimal>> entry : map.entrySet()) {
            Map<String, BigDecimal> grades = entry.getValue();
            if (grades.containsKey(discipline)) {
                Student s = entry.getKey();
                BigDecimal grade = grades.get(discipline);
                // ✅ Correct name order and colon formatting
                result.add(s.getLastName() + "_" + s.getFirstName() + ": " + grade);
            }
        }
        return result;
    }

	@Override
	public Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade) {
        Map<Student, Map<String, BigDecimal>> removed = new TreeMap<Student, Map<String, BigDecimal>>(studentComparator);
        Iterator<Map.Entry<Student, Map<String, BigDecimal>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Student, Map<String, BigDecimal>> entry = it.next();
            for (BigDecimal g : entry.getValue().values()) {
                if (g.compareTo(grade) < 0) {
                    removed.put(entry.getKey(), entry.getValue());
                    it.remove();
                    break;
                }
            }
        }
        return removed;
	}

	@Override
	public Map<BigDecimal, List<Student>> getAndSortAllStudents() {
        Map<BigDecimal, List<Student>> result = new TreeMap<BigDecimal, List<Student>>();
        for (Map.Entry<Student, Map<String, BigDecimal>> e : map.entrySet()) {
            BigDecimal sum = BigDecimal.ZERO;
            int count = 0;
            for (BigDecimal g : e.getValue().values()) {
                sum = sum.add(g);
                count++;
            }
            BigDecimal avg = sum.divide(new BigDecimal(count), 1, BigDecimal.ROUND_HALF_UP);
            if (!result.containsKey(avg)) {
                result.put(avg, new ArrayList<Student>());
            }
            result.get(avg).add(e.getKey());
        }
        return result;
	}
}
