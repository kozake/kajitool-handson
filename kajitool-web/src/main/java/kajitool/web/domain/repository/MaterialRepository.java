package kajitool.web.domain.repository;

import kajitool.dao.mapper.MaterialEntityMapper;
import kajitool.dao.model.MaterialEntity;
import kajitool.dao.model.MaterialEntityExample;
import kajitool.web.domain.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MaterialRepository {
    @Mapper
    public interface MaterialMap {
        MaterialMap INSTANCE = Mappers.getMapper( MaterialMap.class );
        Material toModel(MaterialEntity materialEntity);
    }

    private final MaterialEntityMapper materialMapper;

    public MaterialRepository(final MaterialEntityMapper materialMapper) {
        this.materialMapper = materialMapper;
    }
    public List<Material> selectAll() {
        return materialMapper.selectByExample(new MaterialEntityExample())
                .stream()
                .map(MaterialMap.INSTANCE::toModel)
                .collect(Collectors.toList());
    }
}