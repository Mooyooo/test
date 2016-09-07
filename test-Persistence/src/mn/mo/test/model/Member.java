package mn.mo.test.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity
public class Member implements Serializable {
	private static final long serialVersionUID = 134567L;

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 1, max = 25, message = "1-25 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "full_name")
	private String name;

	@NotNull
	@Digits(fraction = 0, integer = 12, message = "not valid")
	@JoinColumn(name = "phone_number")
	private String phoneNumber;

	public Member() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
