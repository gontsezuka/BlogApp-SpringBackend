package com.zukalover.BlogApplication.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class SubPost {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	@OneToMany(mappedBy="subPost", cascade= CascadeType.ALL)
	private List<Post> posts;
	private Instant createdDate;
	@ManyToOne(targetEntity=User.class,cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
}
