package kajitool.web.service.material;

import kajitool.web.domain.model.Material;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class MaterialService {
    private static final List<Material> materials = Arrays.asList(
            new Material() {{
                setId(1); setName("どうのこうせき");
            }},
            new Material() {{
                setId(2); setName("てっこうせき");
            }},
            new Material() {{
                setId(3); setName("ぎんのこうせき");
            }}
    );
    public List<Material> findAll() {
        return materials;
    }
}