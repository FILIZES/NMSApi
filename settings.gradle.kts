
rootProject.name = "NMSApi"
include("nms")
include("nms:common")
//findProject(":nms:common")?.name = "common"
include("nms:v1_12_R1")
include("nms:v1_13_R2")
//findProject(":nms:v1_12_R1")?.name = "v1_12_R1"
include("api")
include("test-plugin")
