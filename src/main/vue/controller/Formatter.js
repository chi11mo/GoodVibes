class Formatter{
    /**
     * This Method Formate vue Date() into Game data for the Api.
     * @param dateTmp Date() format.
     * @param game game data for Api.
     * @returns {*} game data.
     */
    dateFormatter(dateTmp,game) {
        game.day = dateTmp.getDate() ;

       game.month = this.getMonthFromNumberToString(dateTmp.getMonth());

       game.year = dateTmp.getFullYear();
        return game;
    }

    /**
     * This Method converting number value to Month String in German.
     * @param month number value.
     * @returns {string} value in German month.
     */
    getMonthFromNumberToString(month) {
        switch (month) {
            case 0 :
                return "Januar";
            case 1:
                return "Februar";
            case 2 :
                return "Maerz";
            case 3 :
                return " April";
            case 4 :
                return "Mai";
            case 5 :
                return "Juni";
            case 6 :
                return "Juli";
            case 7 :
                return "August";
            case 8 :
                return "September"
            case 9 :
                return "Oktober";
            case 10 :
                return "November";
            case 11 :
                return "Dezember";

        }
    }

}

export default new Formatter()
