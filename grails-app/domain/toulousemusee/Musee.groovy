package toulousemusee

class Musee {
    String nom
    String horairesOuverture
    int numeroTel
    String accesMetro
    String accesBus
    Adresse adresse

    static constraints = {
        nom blank : false , unique: true
        numeroTel size : 10 //05 61 61 63 33
        accesMetro blank: true
        accesBus blank: true
        adresse nullable: false
    }
}
