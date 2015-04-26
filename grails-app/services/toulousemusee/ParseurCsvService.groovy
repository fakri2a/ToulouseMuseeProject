package toulousemusee

import grails.transaction.Transactional

@Transactional
class ParseurCsvService {
    boolean transactional = true
    MuseeService museeService


    void parse(String pathOfCsv) {
        File f = new File(pathOfCsv)
        Musee unMusee
        Adresse uneAdresse
        Gestionnaire unGestionnaire

        f.splitEachLine(';') { colonne->
            if (colonne[0] != "EQ_NOM_EQUIPEMENT") {
                unGestionnaire = new Gestionnaire(nom: colonne[1])

                uneAdresse = new Adresse(numero: colonne[7],
                        rue: colonne[8],
                        codePostal: colonne [9],
                        ville: colonne[10])

                unMusee = new Musee(nom: colonne[0],
                        horairesOuverture: colonne[2],
                        telephone: colonne[4],
                        accessBus: colonne[5],
                        accessMetro: colonne[6])
                if (!Musee.findByNom(unMusee.nom))
                    museeService.insertOrUpdateMusee(unMusee, uneAdresse, unGestionnaire)
            }
        }

    }
}
