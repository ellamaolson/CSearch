package csearch.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "csprojects")
@SecondaryTable(name = "links")
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
    setTitle(title);
    setDescription(description);
    setProcess(process);
    setDifficulty(difficulty);
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


}
