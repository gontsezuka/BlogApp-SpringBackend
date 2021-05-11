package com.zukalover.BlogApplication.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="token")
public class VerificationToken {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String token;
	@OneToOne(targetEntity=User.class,cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	private Instant expiryDate;
}
