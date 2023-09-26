package org.generation.dataJPA.app.entity;

import java.sql.Timestamp;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Entity is to mark a class  as a entity that persists in a relational database
 * Is to indicate to JPA that the class represents a table in a database and that 
 * the objects of that class can be stored, updated or deleted in that table.
 * Also every instance is considered as a row in the table.
 * 
 */

@Entity
@Data // all getters and setters
@AllArgsConstructor // Constructor with all args
@NoArgsConstructor // Constructor with no args
@Table(name = "customers") // table name in the DB
public class Customer {

	@Id // indicates that this will be a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // indicates how will the primary keys be generated
	private Long id;
	@Column(name = "firstname", nullable = false, length = 150)
	private String firstName;
	@Column(name = "lastname", length = 150)
	private String lastName;
	@Column(name = "email", nullable = false, unique = true, length = 150)
	private String email;
	@Column(name = "password", length = 200)
	private String password;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Timestamp createdAt;

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@OneToMany(mappedBy = "customer")
	@JsonIgnoreProperties("customer")
	private List<OrderProduct> orders = new ArrayList<>();

	/*
	 *  * GenerationType.AUTO: Esta estrategia le permite al proveedor de JPA  *
	 * elegir automáticamente la estrategia de generación más adecuada según  * la
	 * base de datos y la configuración. El comportamiento exacto puede  * variar
	 * dependiendo del proveedor utilizado.  *  * GenerationType.IDENTITY: Esta
	 * estrategia utiliza una columna de identidad de la base de datos para generar
	 * automáticamente los valores de la clave pYrimaria. Es compatible con bases de
	 * datos como MySQL que admiten columnas de autoincremento. En este caso, la
	 * base de datos se encarga de generar y asignar el valor de la clave primaria.
	 * 
	 * GenerationType.SEQUENCE: Esta estrategia utiliza una secuencia de la base de
	 * datos para generar automáticamente los valores de la clave primaria. Se
	 * requiere que la base de datos admita secuencias. El proveedor de JPA utiliza
	 * la secuencia definida en la base de datos para obtener los valores de la
	 * clave primaria.
	 * 
	 * GenerationType.TABLE: Esta estrategia utiliza una tabla especial en la base
	 * de datos para generar los valores de la clave primaria. Se crea una tabla
	 * separada que registra los valores generados para cada entidad. El proveedor
	 * de JPA consulta esta tabla para obtener el próximo valor de la clave
	 * primaria.  *  
	 */

// WITHOUT LOMBOK
//	protected Customer() {
//	}
//
//	public Customer(String firstName, String lastName) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}

}
