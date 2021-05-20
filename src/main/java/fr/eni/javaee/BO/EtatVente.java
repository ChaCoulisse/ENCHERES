package fr.eni.javaee.BO;

public enum EtatVente {
    CREE("vente créée"),
    EN_COURS("en cours"),
    ANNULE("annulée"),
    ENCHERES_TERMINEES("enchères terminées"),
    RETRAIT_EFFECTUE("retrait effectué");

    private String message;

    EtatVente (String message) {
        this.message = message;
    }

    public String getMessage () {
        return message;
    }

    @Override
    public String toString () {
        return "EtatVente{" +
                "message='" + message + '\'' +
                '}';
    }
}
