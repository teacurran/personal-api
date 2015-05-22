package com.wirelust.personalapi.data.repositories;

import com.wirelust.personalapi.data.model.ApiApplication;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Date: 5/22/15
 *
 * @author T. Curran
 */
@Repository
public abstract class ApiApplicationRepository extends AbstractEntityRepository<ApiApplication, String> {

}
