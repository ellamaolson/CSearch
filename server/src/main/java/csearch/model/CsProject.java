package csearch.model;

public class CsProject {
  private String title;
  private String description;
  private String procedure;
  private int difficulty;
  private String[] links;

  public void CsProject() {
    this.title = "";
    this.description = "";
    this.procedure = "";
    this.difficulty = 0;
    this.links = new String[0];
  }

  public void CsProject(String title, String description, String procedure, int difficulty, String[] links) {
    setTitle(title);
    setDescription(description);
    setProcedure(procedure);
    setDifficulty(difficulty);
    setLinks(links);
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

  public String getProcedure() {
    return procedure;
  }

  public void setProcedure(String procedure) {
    this.procedure = procedure;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public String[] getLinks() {
    return links;
  }

  public void setLinks(String[] links) {
    this.links = links;
  }

  public String toString() {
    return "Title: " + getTitle() + "\nDescription: " + getDescription() + "\nProcedure: "
      + getProcedure() + "\nDifficulty: " + getDifficulty() + "\nLinks: " + getLinks().toString();
  }
}
