package demo.data

import com.domingosuarez.validation.constraints.Constrained
import com.domingosuarez.validation.constraints.Constraint
import org.hibernate.validator.constraints.NotBlank

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Transient

/**
 * Created by domix on 01/08/15.
 */
@Entity
@Constrained
class Person {
  @Transient
  List blacklist = [
    'batman',
    'superman'
  ]
  @Id
  String id

  @NotBlank
  String name

  @Constraint(property = 'name', message = 'El nombre esta muy mamón')
  Boolean nombreEsValido() {
    !blacklist.contains(name.toLowerCase())
  }
}
