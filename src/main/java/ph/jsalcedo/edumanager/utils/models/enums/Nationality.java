package ph.jsalcedo.edumanager.data.models.enums;

public enum Nationality {
    CHINESE("Chinese"),
    INDIAN("Indian"),
    AMERICAN("American"),
    INDONESIAN("Indonesian"),
    PAKISTANI("Pakistani"),
    BANGLADESHI("Bangladeshi"),
    JAPANESE("Japanese"),
    PHILIPPINE("Philippine"),
    VIETNAMESE("Vietnamese"),
    TURKISH("Turkish"),
    IRANIAN("Iranian"),
    THAI("Thai"),
    IRAQI("Iraqi"),
    AFGHAN("Afghan"),
    SAUDI("Saudi"),
    UZBEK("Uzbek"),
    MALAYSIAN("Malaysian"),
    YEMENI("Yemeni"),
    NEPALESE("Nepalese"),
    NORTH_KOREAN("North Korean"),
    SYRIAN("Syrian"),
    JORDANIAN("Jordanian"),
    KAZAKH("Kazakh"),
    AUSTRALIAN("Australian"),
    CANADIAN("Canadian"),
    MOROCCAN("Moroccan"),
    PERUVIAN("Peruvian"),
    BURMESE("Burmese"),
    NEW_ZELANDER("New Zealander"),
    MONGOLIAN("Mongolian"),
    FILIPINO("Filipino"),
    ECUADORIAN("Ecuadorian"),
    EGYPTIAN("Egyptian"),
    BRITISH("British"),
    GERMAN("German"),
    FRENCH("French"),
    SOUTH_KOREAN("South Korean"),
    COLOMBIAN("Colombian"),
    SPANISH("Spanish"),
    RUSSIAN("Russian"),
    ARGENTINIAN("Argentinian"),
    ALGERIAN("Algerian"),
    UKRAINIAN("Ukrainian"),
    IRISH("Irish"),
    BRAZILIAN("Brazilian"),
    ITALIAN("Italian"),
    TUNISIAN("Tunisian"),
    MEXICAN("Mexican"),
    SWEDISH("Swedish"),
    DUTCH("Dutch"),
    SWISS("Swiss");

    private final String capitalizedName;

    Nationality(String capitalizedName) {
        this.capitalizedName = capitalizedName;
    }

    public String getCapitalizedName() {
        return capitalizedName;
    }
}