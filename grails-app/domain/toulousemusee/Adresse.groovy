package toulousemusee

class Adresse {
    String numero
    String rue
    int codePostal
    String ville

    static belongsTo = [musee: Musee]

    static constraints = {
        numero blank: true, nullable: true
        rue blank: false, nullable: false
        codePostal min: 31000, max: 39999
        ville blank: false, nullable: false
    }
}
