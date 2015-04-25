package toulousemusee

class DemandeVisiteMusee {
        Date dateDemande

    static constraints = {
        dateDemande nullable: false
    }

    static belongsTo = [
            demandeVisite: DemandeVisite,
            musee        : Musee
    ]
}
