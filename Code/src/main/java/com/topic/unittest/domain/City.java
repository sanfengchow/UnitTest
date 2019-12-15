
package com.topic.unittest.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Eddú Meléndez
 */
@Data
public class City implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String state;

  private String country;

}
