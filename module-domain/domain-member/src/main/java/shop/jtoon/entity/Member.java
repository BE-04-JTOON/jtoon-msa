package shop.jtoon.entity;

import static java.util.Objects.*;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.jtoon.type.ErrorStatus;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "email", nullable = false, unique = true, length = 40, updatable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false, length = 10, updatable = false)
	private String name;

	@Column(name = "nickname", nullable = false, unique = true, length = 30)
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false, updatable = false)
	private Gender gender;

	@Column(name = "phone", nullable = false, unique = true, length = 11)
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "login_type", nullable = false)
	private LoginType loginType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private MemberStatus status = MemberStatus.NORMAL;

	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;

	@Builder
	private Member(String email, String password, String name, String nickname, Gender gender, String phone, Role role,
		LoginType loginType) {
		this.email = requireNonNull(email, ErrorStatus.MEMBER_EMAIL_INVALID_FORMAT.getMessage());
		this.password = requireNonNull(password, ErrorStatus.MEMBER_PASSWORD_INVALID_FORMAT.getMessage());
		this.name = requireNonNull(name, ErrorStatus.MEMBER_NAME_INVALID_FORMAT.getMessage());
		this.nickname = requireNonNull(nickname, ErrorStatus.MEMBER_NICKNAME_INVALID_FORMAT.getMessage());
		this.gender = requireNonNull(gender, ErrorStatus.MEMBER_GENDER_INVALID_FORMAT.getMessage());
		this.phone = requireNonNull(phone, ErrorStatus.MEMBER_PHONE_INVALID_FORMAT.getMessage());
		this.role = role;
		this.loginType = loginType;
	}

	public void updateLastLogin() {
		lastLoginDate = LocalDateTime.now();
	}

	public boolean isAuthor(Member author) {
		return Objects.equals(this.id, author.getId());
	}
}