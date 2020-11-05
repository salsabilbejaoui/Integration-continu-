package tn.esprit.spring.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ConfiguredModelMapper extends org.modelmapper.ModelMapper{
	
	public ConfiguredModelMapper() {
        super();
        this.getConfiguration().setAmbiguityIgnored(true);
        this.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.getConfiguration().setPropertyCondition(Conditions.isNotNull());


}
}
