package com.flightbuddy.flightbuddy;

public enum Airport {

    // POLSKA
    WAW("Warszawa (WAW)", "Polska"),
    GDN("Gdańsk (GDN)", "Polska"),
    KRK("Kraków (KRK)", "Polska"),
    KTW("Katowice (KTW)", "Polska"),

    // NIEMCY
    FRA("Frankfurt (FRA)", "Niemcy"),
    BER("Berlin (BER)", "Niemcy"),
    HAM("Hamburg (HAM)", "Niemcy"),
    MUC("Monachium (MUC)", "Niemcy"),

    // DANIA
    CPH("Kopenhaga (CPH)", "Dania"),

    // LITWA
    VNO("Wilno (VNO)", "Litwa"),

    // ROSJA
    SVO("Moskwa (SVO)", "Rosja"),
    LED("Petersburg (LED)", "Rosja"),
    VVO("Władywostok (VVO)", "Rosja"),

    // BIAŁORUŚ
    MSQ("Mińsk (MSQ)", "Białoruś"),

    // FINLANDIA
    OUL("Oulu (OUL)", "Finlandia"),
    HEL("Helsinki (HEL)", "Finlandia"),

    // SZWECJA
    MMX("Malmo (MMX)", "Szwecja"),
    ARN("Sztokholm (ARN)", "Szwecja"),

    // CZECHY / SŁOWACJA / AUSTRIA
    PRG("Praga (PRG)", "Czechy"),
    BTS("Bratysława (BTS)", "Słowacja"),
    INN("Innsbruck (INN)", "Austria"),
    VIE("Wiedeń (VIE)", "Austria"),

    // SZWAJCARIA
    ZRH("Zurych (ZRH)", "Szwajcaria"),

    // BENELUX
    AMS("Amsterdam (AMS)", "Holandia"),
    BRU("Bruksela (BRU)", "Belgia"),
    LUX("Luksemburg (LUX)", "Luksemburg"),

    // WĘGRY / SŁOWENIA / MOŁDAWIA
    BUD("Budapeszt (BUD)", "Węgry"),
    LJU("Lublana (LJU)", "Słowenia"),
    RMO("Kiszyniów (RMO)", "Mołdawia"),

    // KRAJE BAŁTYCKIE
    TTL("Tallinn (TTL)", "Estonia"),
    RIX("Ryga (RIX)", "Łotwa"),

    // NORWEGIA
    OSL("Oslo (OSL)", "Norwegia"),
    BGO("Bergen (BGO)", "Norwegia"),

    // BAŁKANY
    ZAG("Zagrzeb (ZAG)", "Chorwacja"),
    SPU("Split (SPU)", "Chorwacja"),
    SJJ("Sarajewo (SJJ)", "Bośnia i Hercegowina"),
    TGD("Podgorica (TGD)", "Czarnogóra"),
    PRN("Prisztina (PRN)", "Kosowo"),
    BEG("Belgrad (BEG)", "Serbia"),
    TIA("Tirana (TIA)", "Albania"),
    SKP("Skopje (SKP)", "Macedonia Północna"),

    // RUMUNIA
    OTP("Bukareszt (OTP)", "Rumunia"),
    IAS("Jassy (IAS)", "Rumunia"),
    SBZ("Sybin (SBZ)", "Rumunia"),

    // GRECJA
    ATH("Ateny (ATH)", "Grecja"),
    CHQ("Chania (CHQ)", "Grecja"),
    SKG("Saloniki (SKG)", "Grecja"),

    // BUŁGARIA
    BOJ("Burgas (BOJ)", "Bułgaria"),
    SOF("Sofia (SOF)", "Bułgaria"),

    // TURCJA
    IST("Stambuł (IST)", "Turcja"),
    AYT("Antalya (AYT)", "Turcja"),
    ESB("Ankara (ESB)", "Turcja"),
    ADB("Izmir (ADB)", "Turcja"),

    // HISZPANIA
    MAD("Madryt (MAD)", "Hiszpania"),
    AGP("Malaga (AGP)", "Hiszpania"),
    BCN("Barcelona (BCN)", "Hiszpania"),
    PMI("Palma de Mallorca (PMI)", "Hiszpania"),

    // PORTUGALIA
    LIS("Lizbona (LIS)", "Portugalia"),
    OPO("Porto (OPO)", "Portugalia"),

    // IRLANDIA
    DUB("Dublin (DUB)", "Irlandia"),

    // WŁOCHY
    MXP("Mediolan (MXP)", "Włochy"),
    FCO("Rzym (FCO)", "Włochy"),
    VCE("Wenecja (VCE)", "Włochy"),
    BLQ("Bolonia (BLQ)", "Włochy"),

    // FRANCJA
    CDG("Paryż (CDG)", "Francja"),
    MRS("Marsylia (MRS)", "Francja"),
    NCE("Nicea (NCE)", "Francja"),

    // WIELKA BRYTANIA
    LHR("Londyn (LHR)", "Wielka Brytania"),
    MAN("Manchester (MAN)", "Wielka Brytania"),
    EDI("Edynburg (EDI)", "Wielka Brytania"),

    // ISLANDIA
    RKV("Reykjavik (RKV)", "Islandia");

    private final String displayName;
    private final String country;

    Airport(String displayName, String country) {
        this.displayName = displayName;
        this.country = country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCountry() {
        return country;
    }



    public static Airport fromDisplayName(String text) {
        if (text == null) return null;

        String trimmed = text.trim();
        if (trimmed.isEmpty()) return null;

        for (Airport a : Airport.values()) {
            if (a.getDisplayName().equalsIgnoreCase(trimmed)) {
                return a;
            }
        }
        return null;
    }

}
