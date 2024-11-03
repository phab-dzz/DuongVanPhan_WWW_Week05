package iuh.fit.phandev.backend.enums;

public enum SkillLevel {
    MASTER(0),BEGIN(1),ADVANCED(2),PROFESSIONAL(3),IMTERMEDIATE(4);
    private final int level;
    private SkillLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public static SkillLevel getLevel(int level) {
        return switch (level){
            case 0 -> MASTER;
            case 1 -> BEGIN;
            case 2 -> ADVANCED;
            case 3 -> PROFESSIONAL;
            case 4 -> IMTERMEDIATE;
            default -> IMTERMEDIATE;
        };
    }
}
