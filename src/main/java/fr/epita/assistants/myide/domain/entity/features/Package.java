package fr.epita.assistants.myide.domain.entity.features;

public
class Package extends Maven_features {
public
  Package() { super(FeaturesTypes.Maven.PACKAGE, "mvn package"); }
}