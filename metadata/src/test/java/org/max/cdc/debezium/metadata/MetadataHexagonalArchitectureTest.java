package org.max.cdc.debezium.metadata;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures;
import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.name;

/**
 * For more details related to hexagonal architecture check:
 * https://www.archunit.org/userguide/html/000_Index.html#_architectures
 * https://jeffreypalermo.com/2008/07/the-onion-architecture-part-1/
 */
@AnalyzeClasses(
        packages = "org.max.cdc.debezium.metadata",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class MetadataHexagonalArchitectureTest {

    @ArchTest
    public static final Architectures.OnionArchitecture hexagonalArchitectureRule =
            Architectures.onionArchitecture()
                    .domainModels("..domain.model..")
                    .domainServices("..domain.service..")
                    // we don't have dedicated application services yet, so let's use domain services package for this purpose
                    .applicationServices("..domain.service..")
                    .adapter("controllers", "..api..")
                    .adapter("dao", "..dao..")
                    .ignoreDependency(name(MetadataApplication.class.getCanonicalName()), alwaysTrue());
}

