package com.basic.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(description = "user information ")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  @ApiModelProperty(notes = "name should be greater than two characters")
  @Size(min = 2, message = "Name should have atleast 2 character")
  private String name;

  @OneToMany(mappedBy = "user")
  private List<Post> post;

  public User(Integer id, String name){
    this.id=id;
    this.name=name;
  }
}
