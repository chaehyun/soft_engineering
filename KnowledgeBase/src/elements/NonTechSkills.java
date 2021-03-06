package elements;

public enum NonTechSkills {
    TEAMWORK("Teamwork"), PRESENTATION("Presentation"), COMMUNICATION(
	    "Communication"), DRIVINGLICENCE("Driving licence"), QUICKLEARNING(
	    "Quick learning");

    private String value;

    private NonTechSkills(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return value;
    }
}
