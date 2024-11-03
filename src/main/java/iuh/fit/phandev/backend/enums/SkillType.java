package iuh.fit.phandev.backend.enums;

public enum SkillType {
    SOFT_SKILL(0),
    UNSPECIFIC(1),
    TECHNICAL_SKILL(2);
    private final int value;
    private SkillType(final int value) {
        this.value = value;
    }
    public int getValue() {
        return value;

    }
    public static SkillType fromValue( int value) {
       return  switch (value) {
            case 0 -> SkillType.SOFT_SKILL;
            case 1 -> SkillType.UNSPECIFIC;
            case 2 -> SkillType.TECHNICAL_SKILL;
            default -> SkillType.SOFT_SKILL;

        };
    }
}
