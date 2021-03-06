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
package eu.ueb.acem.dal.bleu.neo4j;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

import eu.ueb.acem.dal.GenericRepository;
import eu.ueb.acem.domain.beans.bleu.neo4j.BesoinNode;
import eu.ueb.acem.domain.beans.bleu.neo4j.ReponseNode;

/**
 * @author Grégoire Colbert @since 2013-11-20
 * 
 */
public interface BesoinRepository extends GenericRepository<BesoinNode> {

	@Query(value = "start n=node(*) where has(n.__type__) and n.__type__ = 'Pedagogical_need' and not (n)-[:aPourBesoinParent]->() return n")
	Collection<BesoinNode> findRoots();

	/*
	 * @Query(value =
	 * "start need=node:indexOfNeeds(name={name}) match (n)-[:aPourBesoinParent]->(need) return n"
	 * ) Collection<BesoinNode> findChildrenOf(@Param("name") String name);
	 * 
	 * @Query(value =
	 * "start need=node:indexOfNeeds(name={name}) match (need)-[:aPourReponse]->(n) return n"
	 * ) Collection<ReponseNode> findAnswersOf(@Param("name") String name);
	 */

	@Query(value = "start need=node({id}) match (n)-[:aPourBesoinParent]->(need) return n")
	Collection<BesoinNode> findAssociatedNeedsOf(@Param("id") Long id);

	@Query(value = "start need=node({id}) match (need)-[:aPourReponse]->(n) return n")
	Collection<ReponseNode> findAssociatedAnswersOf(@Param("id") Long id);
}
