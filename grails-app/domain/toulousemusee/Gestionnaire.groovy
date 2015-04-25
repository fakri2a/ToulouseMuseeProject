package toulousemusee

class Gestionnaire {
    String nom

    static hasMany = [musees : Musee]

    static constraints = {
        nom blank: false, nullable: false
    }

    public setMusee(Musee m) {
        this.musees << m
        m.gestionnaire = this

    }


}
