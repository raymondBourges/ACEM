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
package eu.ueb.acem.dal.bleu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.ueb.acem.dal.DAO;
import eu.ueb.acem.dal.bleu.neo4j.BesoinRepository;
import eu.ueb.acem.domain.beans.bleu.Besoin;
import eu.ueb.acem.domain.beans.bleu.Reponse;
import eu.ueb.acem.domain.beans.bleu.neo4j.BesoinNode;
import eu.ueb.acem.domain.beans.bleu.neo4j.ReponseNode;

/**
 * @author Grégoire Colbert @since 2013-11-20
 *
 */
@Repository("besoinDAO")
public class BesoinDAO implements DAO<Long, Besoin> {

	private final static Logger logger = LoggerFactory.getLogger(BesoinDAO.class);

	@Autowired
	private BesoinRepository repository;

	public BesoinDAO() {
		
	}

	@Override
	public Boolean exists(Long id) {
		return (id != null) ? repository.exists(id) : false;
	}

	@Override
	public Besoin create(Besoin need) {
		logger.debug("Persisting Besoin with name '{}'", need.getName());
		return repository.save((BesoinNode) need);
	}

	@Override
	public Besoin retrieveById(Long id) {
		return (id != null) ? repository.findOne(id) : null;
	}

	@Override
	public Besoin retrieveByName(String name) {
		return repository.findByPropertyValue("name", name);
	}

	public Set<Besoin> retrieveAssociatedNeedsOf(Besoin need) {
		Set<BesoinNode> nodes;
		if (need != null) {
			nodes = repository.findAssociatedNeedsOf(need.getId());
		}
		else {
			nodes = repository.findRoots();
		}

		Set<Besoin> children = new HashSet<Besoin>();
		for (BesoinNode childNode : nodes) {
			children.add(childNode);
		}
		return children;
	}

	public Set<Reponse> retrieveAssociatedAnswersOf(Besoin need) {
		Set<ReponseNode> nodes = repository.findAssociatedAnswersOf(need.getId());
		Set<Reponse> reponses = new HashSet<Reponse>();
		for (ReponseNode node : nodes) {
			reponses.add(node);
		}
		return reponses;
	}

	@Override
	public Set<Besoin> retrieveAll() {
		Iterable<BesoinNode> endResults = repository.findAll();
		Set<Besoin> set = new HashSet<Besoin>();
		if (endResults.iterator() != null) {
			Iterator<BesoinNode> iterator = endResults.iterator();
			while (iterator.hasNext()) {
				set.add(iterator.next());
			}
		}
		return set;
	}
	
	@Override
	public Besoin update(Besoin need) {
		return repository.save((BesoinNode) need);
	}

	@Override
	public void delete(Besoin need) {
		repository.delete((BesoinNode) need);
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
