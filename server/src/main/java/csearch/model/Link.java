package csearch.model;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Link {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String link;
  private int pid; // project id to which the link relates to

  public Link(String link, int pid) {
    this.link = link;
    this.pid = pid;
  }
public Link(){
    this.link = "";
    this.pid = 0;
}
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public int getPid() {
    return pid;
  }

  @Override
  public String toString() {
    return "link:" + getLink()+"\npid: "+getPid();
  }
}
