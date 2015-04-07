package toulousemusee

class Adresse {
    String nom
    String rue
    int codePostal
    String ville

    static constraints = {
        nom blank: false
        rue blank: false
        codePostal inList: [3100,  31200, 31300, 31400, 31500]
        ville blank: false
    }
}
