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
package eu.ueb.acem.domain.beans.gris.neo4j;

import static org.neo4j.graphdb.Direction.OUTGOING;

import java.util.Collection;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import eu.ueb.acem.domain.beans.bleu.Besoin;
import eu.ueb.acem.domain.beans.bleu.Reponse;
import eu.ueb.acem.domain.beans.bleu.neo4j.BesoinNode;
import eu.ueb.acem.domain.beans.bleu.neo4j.ReponseNode;
import eu.ueb.acem.domain.beans.gris.Gestionnaire;
import eu.ueb.acem.domain.beans.jaune.Ressource;
import eu.ueb.acem.domain.beans.jaune.neo4j.RessourceNode;

/**
 * @author Grégoire Colbert @since 2013-11-20
 * 
 */
@NodeEntity
@TypeAlias("Administrator")
public class GestionnaireNode extends PersonneNode implements Gestionnaire {

	private static final long serialVersionUID = -3193454107919543890L;

	@GraphId
	private Long id;

	@Indexed(indexName = "indexGestionnaire")
	private String name;

	@RelatedTo(elementClass = BesoinNode.class, type = "redigeBesoin", direction = OUTGOING)
	private Collection<Besoin> besoins;

	@RelatedTo(elementClass = ReponseNode.class, type = "redigeReponse", direction = OUTGOING)
	private Collection<Reponse> reponses;

	@RelatedTo(elementClass = RessourceNode.class, type = "redigeRessource", direction = OUTGOING)
	private Collection<Ressource> ressources;

	public GestionnaireNode() {
	}

	public GestionnaireNode(String name) {
		this.name = name;
	}

	@Override
	public Collection<Besoin> getBesoins() {
		return besoins;
	}

	@Override
	public Collection<Reponse> getReponses() {
		return reponses;
	}

	@Override
	public Collection<Ressource> getRessources() {
		return ressources;
	}

}
