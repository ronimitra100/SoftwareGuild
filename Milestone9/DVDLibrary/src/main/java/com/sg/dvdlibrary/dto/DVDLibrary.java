package com.sg.dvdlibrary.dto;

public class DVDLibrary {

    String title;
    String releaseDate;
    String mpaaRating;
    String director;
    String studio;
    String userRating;

    public DVDLibrary(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVDLibrary other = (DVDLibrary) obj;
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.releaseDate == null) ? (other.releaseDate != null) : !this.releaseDate.equals(other.releaseDate)) {
            return false;
        }
        if ((this.mpaaRating == null) ? (other.mpaaRating != null) : !this.mpaaRating.equals(other.mpaaRating)) {
            return false;
        }
        if ((this.director == null) ? (other.director != null) : !this.director.equals(other.director)) {
            return false;
        }
        if ((this.studio == null) ? (other.studio != null) : !this.studio.equals(other.studio)) {
            return false;
        }
        if ((this.userRating == null) ? (other.userRating != null) : !this.userRating.equals(other.userRating)) {
            return false;
        }
        return true;
    }
    
    
}
