package roteriza.utils;

import org.springframework.transaction.annotation.Transactional;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import roteriza.utils.mapper.IgnoreLazyCodeGenerationStrategy;

public class MapperUtils<E, D> {

	public static MapperFactory factory = createFactory();
	public static MapperFacade mapper = factory.getMapperFacade();

	private Class<E> entityClass;
	private Class<D> dtoClass;

	private static MapperFactory createFactory() {
		return new DefaultMapperFactory.Builder().codeGenerationStrategy(new IgnoreLazyCodeGenerationStrategy())
				.build();
	}

	public MapperUtils(Class<E> entityClass, Class<D> dtoClass) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}

	@Transactional(readOnly = true)
	public D toDTO(E entity) {
		return mapper.map(entity, dtoClass);
	}

	public E toEntity(D dto, String... exclusions) {
		MapperFactory instanceFactory = createFactory();
		ClassMapBuilder<E, D> classMap = instanceFactory.classMap(entityClass, dtoClass);
		for (String exc : exclusions) {
			classMap.exclude(exc);
		}

		classMap.byDefault().register();
		return instanceFactory.getMapperFacade().map(dto, entityClass);
	}

	public void updateEntity(E entity, D dto, String... exclusions) {
		MapperFactory instanceFactory = createFactory();
		ClassMapBuilder<E, D> classMap = instanceFactory.classMap(entityClass, dtoClass);
		for (String exc : exclusions) {
			classMap.exclude(exc);
		}
		classMap.byDefault().register();

		instanceFactory.getMapperFacade(entityClass, dtoClass).mapReverse(dto, entity);
	}

}