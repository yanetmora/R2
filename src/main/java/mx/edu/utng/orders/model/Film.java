package mx.edu.utng.orders.model;



public class Film {
    private String idFilm;
    private String title;
    private String description;
    private String releaseYear;
    private String language;
    private String rentalDuration;
    private String rentalRate;
    private String lenght;
    private String replacementCost;
    private String rating;
    private String lastUpdate;
    private String specialFeatures;
    private String fulltext;

    public Film(String idFilm, String title, String description,
                String releaseYear, String language, String rentalDuration, String rentalRate,
                String lenght, String replacementCost, String rating, String lastUpdate, String specialFeatures, String fulltext) {
        this.idFilm = idFilm;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.lenght = lenght;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.lastUpdate = lastUpdate;
        this.specialFeatures = specialFeatures;
        this.fulltext = fulltext;
    }

    public Film() {
        this("","","","","","","","","","","","","");
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(String rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public String getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(String rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(String replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    @Override
    public String toString() {
        return "Film{" +
                "idFilm='" + idFilm + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", language='" + language + '\'' +
                ", rentalDuration='" + rentalDuration + '\'' +
                ", rentalRate='" + rentalRate + '\'' +
                ", lenght='" + lenght + '\'' +
                ", replacementCost='" + replacementCost + '\'' +
                ", rating='" + rating + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", specialFeatures='" + specialFeatures + '\'' +
                ", fulltext='" + fulltext + '\'' +
                '}';
    }
}
