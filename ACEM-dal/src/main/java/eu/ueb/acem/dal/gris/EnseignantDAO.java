/**
 *     Copyright Grégoire COLBERT 2013
 * 
 *     This file is part of Atelier de Création d'Enseignement Multimodal (ACEM).
 * 
 *     ACEM is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     ACEM is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with ACEM.  If not, see <http://www.gnu.org/licenses/>
 */
package eu.ueb.acem.dal.gris;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.ueb.acem.dal.DAO;
import eu.ueb.acem.dal.gris.neo4j.EnseignantRepository;
import eu.ueb.acem.domain.beans.gris.Enseignant;
import eu.ueb.acem.domain.beans.gris.neo4j.EnseignantNode;

/**
 * @author Grégoire Colbert @since 2013-12-11
 * 
 */
@Repository("enseignantDAO")
public class EnseignantDAO implements DAO<Long, Enseignant> {

	/**
	 * For Logging.
	 */
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(EnseignantDAO.class);

	@Autowired
	private EnseignantRepository repository;

	public EnseignantDAO() {

	}

	@Override
	public Boolean exists(Long id) {
		return (id != null) ? repository.exists(id) : false;
	}

	@Override
	public Enseignant create(Enseignant enseignant) {
		return repository.save((EnseignantNode) enseignant);
	}

	@Override
	public Enseignant retrieveById(Long id) {
		return (id != null) ? repository.findOne(id) : null;
	}

	@Override
	public Enseignant retrieveByName(String nom) {
		return repository.findByPropertyValue("nom", nom);
	}

	@Override
	public Collection<Enseignant> retrieveAll() {
		Iterable<EnseignantNode> endResults = repository.findAll();
		Collection<Enseignant> collection = new HashSet<Enseignant>();
		if (endResults.iterator() != null) {
			Iterator<EnseignantNode> iterator = endResults.iterator();
			while (iterator.hasNext()) {
				collection.add(iterator.next());
			}
		}
		return collection;
	}

	@Override
	public Enseignant update(Enseignant enseignant) {
		return repository.save((EnseignantNode) enseignant);
	}

	@Override
	public void delete(Enseignant enseignant) {
		repository.delete((EnseignantNode) enseignant);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public Long count() {
		return repository.count();
	}

}
