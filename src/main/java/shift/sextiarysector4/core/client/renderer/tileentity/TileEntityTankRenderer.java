package shift.sextiarysector4.core.client.renderer.tileentity;

import java.awt.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.biome.BiomeColors;
import org.lwjgl.opengl.GL11;
import shift.sextiarysector4.core.tileentity.TileEntityTank;

public class TileEntityTankRenderer<T extends TileEntityTank> extends TileEntityRenderer<T> {

    public void render(T tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        super.render(tileEntityIn, x, y, z, partialTicks, destroyStage);

        GlStateManager.pushMatrix();
        GlStateManager.pushLightingAttrib();

        //GLåæå
        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();

        buffer.setTranslation(x, y, z);

        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        TextureAtlasSprite still = Minecraft.getInstance().getTextureMap().getAtlasSprite("block/water_still");
        TextureAtlasSprite flow = Minecraft.getInstance().getTextureMap().getAtlasSprite("block/water_still");


        double posY = 0.5;

        int waterColor = BiomeColors.getWaterColor(getWorld(), tileEntityIn.getPos());
        Color color = new Color(waterColor, false);

        VoxelShape shape = tileEntityIn.getBlockState().getShape(getWorld(), tileEntityIn.getPos());
        AxisAlignedBB box = shape.getBoundingBox();
        box = box.grow(-0.001f);

        //ã¡ã¢
        // U=X,V=Y
        // (X,Z) = (ç¸¦,æ¨ª)
        // Posã®æå®ããé çª
        // â ââ 
        // ââ â
        // â ââ 

        //WEST
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(box.minX + 0.001f, box.minY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX + 0.001f, box.minY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX + 0.001f, box.maxY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX + 0.001f, box.maxY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tess.draw();

        //SOUTH å·¦
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(box.minX, box.maxY, box.maxZ).tex(flow.getInterpolatedU(box.minX * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX, box.minY, box.maxZ).tex(flow.getInterpolatedU(box.minX * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.minY, box.maxZ).tex(flow.getInterpolatedU(box.maxX * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.maxY, box.maxZ).tex(flow.getInterpolatedU(box.maxX * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tess.draw();

        //EAST
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(box.maxX, box.minY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.minY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.maxY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.maxY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tess.draw();

        //NORTH
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(box.maxX, box.maxY, box.minZ).tex(flow.getInterpolatedU(box.maxX * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.minY, box.minZ).tex(flow.getInterpolatedU(box.maxX * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX, box.minY, box.minZ).tex(flow.getInterpolatedU(box.minX * 16), flow.getInterpolatedV(box.minY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX, box.maxY, box.minZ).tex(flow.getInterpolatedU(box.minX * 16), flow.getInterpolatedV(box.maxY * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tess.draw();

        //DOWN
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(box.minX, box.minY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.minX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.minY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.maxX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.minY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.maxX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX, box.minY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.minX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tess.draw();

        //UP
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(box.minX, box.maxY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.minX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.minX, box.maxY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.minX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.maxY, box.maxZ).tex(flow.getInterpolatedU(box.maxZ * 16), flow.getInterpolatedV(box.maxX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        buffer.pos(box.maxX, box.maxY, box.minZ).tex(flow.getInterpolatedU(box.minZ * 16), flow.getInterpolatedV(box.maxX * 16)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tess.draw();

        buffer.setTranslation(0, 0, 0);

        //GL
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();

        GlStateManager.popAttrib();
        GlStateManager.popMatrix();

    }

}
