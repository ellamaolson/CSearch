package csearch.model;

import javax.persistence.*;

@Entity
@Table(name = "csprojects")
public class CsProject {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pid")
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
    setTitle(title);
    setDescription(description);
    setProcess(process);
    setDifficulty(difficulty);    
  }

  public int getId() { return id; }

  public void setId() { this.id = id; }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title != null && !title.isEmpty()) this.title = title;
    else this.title = "No Title";
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description != null) this.description = description;
    else this.description = "";
  }

  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    if (process != null) this.process = process;
    else this.process = "";
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    if (difficulty >= 1 && difficulty <= 5) this.difficulty = difficulty;
    else this.difficulty = 0;
  }

  @Override
  public String toString() {
    return "Title: " + getTitle() + "\nDescription: " + getDescription() + "\nProcess: "
      + getProcess() + "\nDifficulty: " + getDifficulty();
  }
}
