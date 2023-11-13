package edu.pnu.domain;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "boardList")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	
	private String password;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
	
	
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	@Builder.Default
	private List<Board> boardList = new ArrayList<>();
}
