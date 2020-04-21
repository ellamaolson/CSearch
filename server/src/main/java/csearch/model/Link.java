package csearch.model;

import javax.persistence.*;

@Entity
@Table(name = "linkstable")
public class Link {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String link;

  private int p_id;

  public Link(String link, int p_id) {
    this.link = link;
    this.p_id =p_id;

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


}
