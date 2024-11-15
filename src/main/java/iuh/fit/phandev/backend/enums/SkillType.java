package iuh.fit.phandev.backend.enums;

public enum SkillType {
    INTERPERSONAL_SKILLS(0),
    GENERAL_SKILLS(1),
    PROFESSIONAL_SKILLS(2);
    private final int value;
    private SkillType(final int value) {
        this.value = value;
    }
    public int getValue() {
        return value;

    }
    public static SkillType fromValue( int value) {
       return  switch (value) {
           case 0 -> SkillType.INTERPERSONAL_SKILLS;
           case 1 -> SkillType.GENERAL_SKILLS;
           case 2 -> SkillType.PROFESSIONAL_SKILLS;
           default -> SkillType.INTERPERSONAL_SKILLS;

        };
    }
}
