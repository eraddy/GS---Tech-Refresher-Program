# 📘 Prompt Template Reference (README)

A concise reference guide summarizing the generic use-case templates you created.  
Each template describes its purpose and structure so you can reuse it for future tasks.

---

## 🟦 Personalized Structured Plan Generation

**Use Case:**  
Generate a multi-day personalized plan (diet plan, schedule, activity plan, etc.) tailored to user preferences, goals, restrictions, and constraints.

**Template Concept:**
- Provide user-specific context (preferences, allergies, goals, restrictions, budget, complexity).
- Define constraints such as variety, no repetition, simplicity, calorie limits, cultural diversity, etc.
- Output follows a **multi-day structured format**, where each day contains:
    - Named components (e.g., breakfast/lunch/dinner or task segments)
    - Short 2–4 step instructions
    - Required items or ingredients
    - Optional metrics (calories, time, difficulty, etc.)

---

## 🟩 Temperature-Controlled Response Generation

**Use Case:**  
Generate multiple versions of the same content with varying creativity levels using temperature control (e.g., 0.2, 0.5, 0.9).

**Template Concept:**
- Provide a base context or scenario.
- Request *three or more versions* of output with different temperature levels:
    - Low temperature → logical, predictable, controlled
    - Medium temperature → moderately creative, balanced
    - High temperature → bold, imaginative, unconventional
- Clearly label each version by temperature.
- After each version, include a short commentary describing how temperature influenced:
    - Tone
    - Creativity
    - Variation in ideas or storyline

---

## 🟧 Multi-Step Logical Task Planning (Chain-of-Thought Style)

**Use Case:**  
Break down a complex task into actionable, logical steps with clear sub-tasks.

**Template Concept:**
- Provide the main task context (resources, challenges, goals, environment).
- Organize the plan into major sections such as:
    - Participant or stakeholder engagement
    - Scheduling and planning
    - Resource and material gathering
    - Role assignment
    - Logistics and coordination
    - Execution steps
    - Post-task follow-up
- Each section includes:
    - Purpose
    - Sub-tasks
    - Actionable instructions

---

## 🟨 Comparative Analysis and Recommendation Framework

**Use Case:**  
Compare two or more items side-by-side using structured criteria and provide a recommendation.

**Template Concept:**
- Define the items being compared.
- Present comparison in a structured table including attributes like:
    - Cost
    - Performance
    - Features
    - Quality
    - User experience
    - Efficiency
- Follow the table with:
    - Strengths of Item A
    - Strengths of Item B
    - Recommendation based on user type or scenario

---

## 🟥 Weekly Training or Routine Structuring

**Use Case:**  
Create a detailed multi-day routine (workouts, learning schedule, productivity plan, etc.) based on user profile and goals.

**Template Concept:**
- Provide user details (age, schedule, goals, lifestyle).
- Build a structured daily routine (e.g., weekly split, rotation pattern, progressive schedule).
- Each day includes:
    - Specific tasks/exercises with quantified sets, reps, time, or intensity
    - Variations when repeating a similar routine
    - Warm-up and cool-down or preparation and recovery steps
- Include:
    - Progression or improvement guidelines
    - Safety or recovery tips
    - Supporting habits such as sleep, diet, hydration, mobility

---

# ✅ Summary

This README gives you:
- Generic, reusable template descriptions
- Use-case–based headings
- Structures adaptable for any domain or scenario
- Clean Markdown format ready for GitHub, Notion, or documentation

