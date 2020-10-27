package edu.miu.groupx.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class cardno 
{
	@Id
	@Column(name = "cardno")
	private String cardno;
	
	public cardno() {}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	
}
