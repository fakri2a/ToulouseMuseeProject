package toulousemusee

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accessMetro
    String accessBus
    Adresse adresse


    static hasOne = [
            gestionnaire: Gestionnaire,
            //adresse: Adresse
    ]

    static hasMany = [
            demandeVisiteMusees: DemandeVisiteMusee
    ]

    static constraints = {
        nom nullable: false, blank: false
        horairesOuverture nullable: false, blank: false
        telephone nullable: false, blank: false
        accessMetro nullable: true, blank: true
        accessBus nullable: true, blank: true
    }

    public String toString() {
        "${nom}"
    }
}