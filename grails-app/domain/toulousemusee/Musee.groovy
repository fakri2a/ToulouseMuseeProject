package toulousemusee

class Musee {
    String nom
    String horairesOuverture
    int numeroTel
    String accesMetro
    String accesBus


    static constraints = {
        nom blank : false, nullable: false
        horairesOuverture time: true, blank: true
        numeroTel blank: true, nullable: true
        accesMetro blank: true, nullable: true
        accesBus blank: true, nullable: true
    }

    static belongsTo = [
            gestionnaire: Gestionnaire,
            adresse     : Adresse
    ]
}
