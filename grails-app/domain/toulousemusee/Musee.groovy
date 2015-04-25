package toulousemusee

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accessMetro
    String accessBus

    static hasOne = [
            gestionnaire: Gestionnaire,
            adresse: Adresse
    ]

    static hasMany = [
            demandeVisiteMusees: DemandeVisiteMusee
    ]

    static constraints = {
        nom nullable: false, blank: false
        horairesOuverture nullable: false, blank: false
        telephone nullable: false, blank: false
        accessMetro nullable: false, blank: true
        accessBus nullable: false, blank: true
    }

    public setAdresse(Adresse uneAdresse) {
        this.adresse = uneAdresse
        uneAdresse.musee = this
    }

    public String toString() {
        "${nom}"
    }
}