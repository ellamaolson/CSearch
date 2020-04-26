package csearch.model;

import javax.persistence.*;

@Entity
@Table(name = "csprojects")
public class CsProject {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "p_id")
  private int id;
  private String title;
  private String description;
  private String process;
  private int difficulty;

  public CsProject() {
    setTitle("");
    setDescription("");
    setProcess("");
    setDifficulty(0);
  }

  public CsProject(String title, String description, String process, int difficulty) {
    if (title != null && !title.isEmpty()) this.title = title;
    else setDefault("title");
    if (description != null) this.description = description;
    else setDefault("description");
    if (process != null) this.process = process;
    else setDefault("process");
    this.process = process;
    if (difficulty >= 1 && difficulty <= 5) this.difficulty = difficulty;
    else setDefault("difficulty");
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

  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  @Override
  public String toString() {
    return "Title: " + getTitle() + "\nDescription: " + getDescription() + "\nProcess: "
      + getProcess() + "\nDifficulty: " + getDifficulty();
  }

  public int getId() {
    return id;
  }

  public void setDefault(String name) {
    switch (name) {
      case "title":
        this.title = "No title";
        break;
      case "description":
        this.description = "";
        break;
      case "process":
        this.process = "";
        break;
      case "difficulty":
        this.difficulty = 1;
        break;
    }
  }
}
