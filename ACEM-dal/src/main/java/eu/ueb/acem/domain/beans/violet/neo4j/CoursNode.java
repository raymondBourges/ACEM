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
package eu.ueb.acem.domain.beans.violet.neo4j;

import static org.neo4j.graphdb.Direction.OUTGOING;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import eu.ueb.acem.domain.beans.violet.Cours;
import eu.ueb.acem.domain.beans.violet.UniteEnseignement;

/**
 * @author gcolbert @since 2013-11-20
 *
 */
@NodeEntity
public class CoursNode implements Cours {

	private static final long serialVersionUID = -4467389921550574916L;

	@GraphId private Long id;
	
	@Indexed(indexName = "rechercher-cours") private String intitule;

	private String duree;
	
	@RelatedTo(elementClass = UniteEnseignement.class, type = "estUnePartieDe", direction = OUTGOING)
	private UniteEnseignement ue;
	
	public CoursNode() {
	}
	
	public CoursNode(String intitule) {
		this.intitule = intitule;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public UniteEnseignement getUe() {
		return ue;
	}

}