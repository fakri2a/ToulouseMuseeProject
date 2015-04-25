package toulousemusee

import grails.transaction.Transactional

@Transactional
class ParseurCsvService {
    boolean transactional = true

    def parse(String pathOfCsv) {
        MuseeService unMuseeService

        File f = new File(pathOfCsv)
        Musee unMusee
        Adresse uneAdresse
        Gestionnaire unGestionnaire

        f.splitEachLine(';') { colonne->
            if (colonne[0] != "EQ_NOM_EQUIPEMENT") {
                if (!Gestionnaire.findByNom(colonne[1]))
                    unGestionnaire = new Gestionnaire(colonne[1])

                uneAdresse = new Adresse(numero: colonne[7],
                        rue: colonne[8],
                        codePostal: colonne [9],
                        ville: colonne[10])

                unMusee = new Musee(nom: colonne[0],
                        horairesOuverture: colonne[2],
                        telephone: colonne[4],
                        accessBus: colonne[5],
                        accessMetro: colonne[6])

                unMusee = unMuseeService.insertOrUpdateMusee(unMusee, uneAdresse, unGestionnaire)

            }

        }

    }
}
