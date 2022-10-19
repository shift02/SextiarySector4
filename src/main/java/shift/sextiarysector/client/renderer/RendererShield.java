package shift.sextiarysector.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.SextiarySector4;

public class RendererShield extends BlockEntityWithoutLevelRenderer {

    public static final Material PLASTIC_SHIELD_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(SextiarySector4.MOD_ID, "entity/plastic_shield"));

    private ShieldModel shieldModel;

    public final EntityModelSet entityModelSet;

    public RendererShield() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        entityModelSet = Minecraft.getInstance().getEntityModels();
    }

    public void onResourceManagerReload(ResourceManager pResourceManager) {
        this.shieldModel = new ShieldModel(this.entityModelSet.bakeLayer(ModelLayers.SHIELD));
    }

    public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Item item = pStack.getItem();

        if (item != SSItems.PLASTIC_SHIELD.get()) {
            return;
        }

        pPoseStack.pushPose();
        pPoseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = PLASTIC_SHIELD_TEXTURE;
        VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(pBuffer, RenderType.entityTranslucent(material.atlasLocation()), true, pStack.hasFoil()));
        this.shieldModel.handle().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        this.shieldModel.plate().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        pPoseStack.popPose();

    }
}
