package toulousemusee

class Musee {
    String nom
    String horairesOuverture
    int numeroTel
    String accesMetro
    String accesBus
    Adresse adresse

    static constraints = {
        nom blank : false
        horairesOuverture time: true, blank: true
        numeroTel blank: true
        accesMetro blank: true
        accesBus blank: true
        adresse nullable: true
    }
}
