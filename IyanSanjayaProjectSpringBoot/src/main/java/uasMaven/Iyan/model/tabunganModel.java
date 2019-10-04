package uasMaven.Iyan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="tabungan_tbl")
@EntityListeners(AuditingEntityListener.class)
public class tabunganModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nik;
	private String nama;
	private int kredit;
	private int debet;
	private int saldo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date craete_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getKredit() {
		return kredit;
	}

	public void setKredit(int kredit) {
		this.kredit = kredit;
	}

	public int getDebet() {
		return debet;
	}

	public void setDebet(int debet) {
		this.debet = debet;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Date getCraete_time() {
		return craete_time;
	}

	public void setCraete_time(Date craete_time) {
		this.craete_time = craete_time;
	}

}
