package com.epam.rd.autocode.set;

import java.util.*;

public class Project {

    private final List<Role> roles = new ArrayList<>();

    public Project(Role... roles) {
        for (Role r : roles) {
            this.roles.add(r);
        }
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getConformity(Set<Member> team) {
        List<Entry> projectEntries = new ArrayList<>();
        for (Role role : roles) {
            for (Skill skill : role.getSkills()) {
                projectEntries.add(new Entry(role.getLevel(), skill));
            }
        }

        int total = projectEntries.size();
        int matched = 0;

        List<Entry> teamEntries = new ArrayList<>();
        for (Member member : team) {
            for (Skill skill : member.getSkills()) {
                teamEntries.add(new Entry(member.getLevel(), skill));
            }
        }

        Iterator<Entry> teamIt;
        for (Entry projectEntry : projectEntries) {
            teamIt = teamEntries.iterator();
            while (teamIt.hasNext()) {
                Entry teamEntry = teamIt.next();
                if (teamEntry.skill == projectEntry.skill) { // match skill, regardless of level
                    matched++;
                    teamIt.remove(); // consume once
                    break;
                }
            }
        }

        return (matched * 100) / total;
    }





    // Nested static Entry class
    private static class Entry {
        private final Level level;
        private final Skill skill;

        Entry(Level level, Skill skill) {
            this.level = level;
            this.skill = skill;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry entry = (Entry) o;
            return level == entry.level && skill == entry.skill;
        }

        @Override
        public int hashCode() {
            int result = level != null ? level.hashCode() : 0;
            result = 31 * result + (skill != null ? skill.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "<" + level + ", " + skill + ">";
        }
    }
}
